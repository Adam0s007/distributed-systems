from request_handler import RequestHandler

def display_commands():
    commands = """
    ----- Commands -----
    commands
    exit  
    services
    describe <object>
    allMethods
    serviceMethods <serviceName>
    calculate <SUM|AVG|MOST_FREQUENT|RANGE|MULT|MIN|MAX|VARIANCE|STDDEV> <num1 num2 num3...>
    streamEulerNumber <terms>
    listEulerNumber <terms>
    findFibonacciNumbers
    --------------------
    """
    print(commands)

def collect_fibonacci_numbers():
    numbers = []
    print("Starting Fibonacci stream... Enter numbers one by one (type 'close' to end):")
    while True:
        number_input = input(">> ")
        if number_input.lower() == 'close':
            break
        elif number_input.isdigit():
            numbers.append(int(number_input))
        else:
            print("Please enter a valid integer or 'close' to end.")
    return numbers


def main():
    server_address = "127.0.0.5:50051"  # Placeholder server address
    handler = RequestHandler(server_address)
    display_commands()
    while True:
        command_input = input(">> ")
        args = command_input.split()
        command = args[0]
        if command == "exit":
            break
        elif command == "commands":
            display_commands()
        elif command == "services":
            print(handler.execute_curl_command("list"))
        elif command == "describe":
            if len(args) > 1:
                print(handler.execute_curl_command("describe", obj=args[1]))
            else:
                print("Object name is required.")
        elif command == "allMethods":
            handler.get_all_methods()
        elif command == "serviceMethods":
            if len(args) > 1:
                print(handler.execute_curl_command("list", obj=args[1]))
            else:
                print("Service name is required.")
        elif command == "calculate":
            if len(args) > 2:
                operation = args[1]
                try:
                    numbers = list(map(int, args[2:]))
                    data = {"opType": operation, "args": numbers}
                    print(handler.execute_curl_command(f"calculator.CalculatorService/Operation", data=data))
                except ValueError:
                    print("Invalid number(s) entered.")
            else:
                print("Operation and numbers are required.")
        elif command == "streamPrimeNumbers":
            if len(args) > 1:
                max_value = int(args[1])
                handler.execute_curl_command("primes.PrimesService/StreamPrimeNumbers", data={"max": max_value}, stream=True)
            else:
                print("Max value is required.")
        elif command == "listPrimeNumbers":
            if len(args) > 1:
                max_value = int(args[1])
                print(handler.execute_curl_command("primes.PrimesService/ListPrimeNumbers", data={"max": max_value}))
            else:
                print("Max value is required.")
        elif command == "streamEulerNumber":
            if len(args) > 1:
                terms = int(args[1])
                handler.execute_curl_command("euler.EulerService/StreamEulerNumber", data={"terms": terms}, stream=True)
            else:
                print("Number of terms is required.")
        elif command == "listEulerNumber":
            if len(args) > 1:
                terms = int(args[1])
                print(handler.execute_curl_command("euler.EulerService/ListEulerNumber", data={"terms": terms}))
            else:
                print("Number of terms is required.")
        elif command == "findFibonacciNumbers":
            numbers = collect_fibonacci_numbers()
            if numbers:
                try:
                    print("Finding Fibonacci numbers...")
                    handler.execute_curl_command("fibonacci.FibonacciService/FindFibonacciNumbers",data={"numbers": [{"number": num} for num in numbers]}, stream=True)
                except Exception as e:
                    print("An error occurred while sending numbers:", e)
            else:
                print("No numbers to send.")
        else:
            print("Invalid command. Type 'commands' to see available commands.")

if __name__ == '__main__':
    main()