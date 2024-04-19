import numpy as np

def merge_sort(arr):
    def merge(arr, temp_arr, left, mid, right):
        i, j, k = left, mid + 1, left

        while i <= mid and j <= right:
            if arr[i] <= arr[j]:
                temp_arr[k] = arr[i]
                i += 1
            else:
                temp_arr[k] = arr[j]
                j += 1
            k += 1

        while i <= mid:
            temp_arr[k] = arr[i]
            i += 1
            k += 1

        while j <= right:
            temp_arr[k] = arr[j]
            j += 1
            k += 1

        for loop_var in range(left, right + 1):
            arr[loop_var] = temp_arr[loop_var]

    def _merge_sort(arr, temp_arr, left, right):
        if left < right:
            mid = (left + right) // 2
            _merge_sort(arr, temp_arr, left, mid)
            _merge_sort(arr, temp_arr, mid + 1, right)
            merge(arr, temp_arr, left, mid, right)

    _merge_sort(arr, arr[:], 0, len(arr) - 1)
    return arr

# Example usage
arr = [12, 11, 13, 5, 6, 7]
arr_new = np.array_split(arr,2)[0]
arr = merge_sort(arr_new)
print("Sorted array is:", arr)
