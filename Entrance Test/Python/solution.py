import random

sizeBound = 100
numberBound = 100


def solution(n):
    if not isinstance(n, int) or n <= 0:
        raise Exception("Illegal argument")

    arrays = []
    sizes = random.sample(range(1, sizeBound), n)

    for i in range(0, n):
        array = random.sample(range(1, sizeBound), sizes[i])
        array.sort(reverse=i % 2 != 0)
        arrays.append(array)

    return arrays


print(solution(5))
