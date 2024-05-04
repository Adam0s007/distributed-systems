import subprocess
import json

class RequestHandler:
    def __init__(self, server_address):
        self.server = server_address
        self.curl_command = "grpcurl.exe"
        self.plain_text = "-plaintext"

    def execute_curl_command(self, method, data=None, obj=None, stream=False):
        args = [self.curl_command, self.plain_text]
        if data:
            args += ["-d", json.dumps(data)]
        
        args += [self.server, method]
        
        if obj:
            args.append(obj)

        process = subprocess.Popen(args, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)

        if not stream:
            output, errors = process.communicate()
            if process.returncode == 0:
                return output.strip()
            else:
                return "Error: " + errors.strip()
        else:
            while True:
                output = process.stdout.readline()
                if output == '' and process.poll() is not None:
                    break
                if output:
                    print(output.strip())
            if process.returncode != 0:
                print("Stream Error:", process.stderr.read())


    def get_all_methods(self):
        services = self.execute_curl_command("list").split()
        for service in services:
            methods = self.execute_curl_command("list", obj=service).split()
            print(f"Service: {service}")
            for method in methods:
                print(f"  {method}")


    def stream_fibonacci_numbers(self):
            process = self.execute_curl_command("fibonacci.FibonacciService/FindFibonacciNumbers", stream=True)
            try:
                while True:
                    number = input(">> ")
                    if number == "close":
                        process.stdin.close()
                        break
                    process.stdin.write(number + '\n')
                    process.stdin.flush()
                    response = process.stdout.readline()
                    print("Received:", response.strip())
                process.wait()
            except KeyboardInterrupt:
                process.terminate()
            except Exception as e:
                print("Error during streaming:", str(e))
            finally:
                if process.returncode != 0:
                    print("Stream Error:", process.stderr.read())

