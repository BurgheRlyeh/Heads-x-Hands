#include <stdio.h>
#include <assert.h>
#include <malloc.h>
#include <time.h>

const int sizeBound = 100;
const int numberBound = 100;

int* randomUniqueNumbers(int n) {
    srand((unsigned)time(NULL));

    int* array = (int*)malloc(n * sizeof(int));
    int size = 0;

    for (int i = 0; i < n; ++i) {
        size += rand() % (sizeBound / n);
        array[i] = size;
    }

    return array;
}

int cmpAscOrder(const void* a, const void* b) {
    return *(int*)a - *(int*)b;
}
int cmpDescOrder(const void* a, const void* b) {
    return *(int*)b - *(int*)a;
}

int** solution(int n) {
    assert(0 < n);

    srand((unsigned)time(NULL));

    int** arrays = (int**)malloc(n * sizeof(int*));
    int* sizes = randomUniqueNumbers(n);

    for (int i = 0; i < n; ++i) {
        arrays[i] = (int*)malloc(sizes[i] * sizeof(int));
        for (int j = 0; j < sizes[i]; ++j) {
            arrays[i][j] = rand() % numberBound;
        }

        qsort(arrays[i],
              sizes[i],
              sizeof(int),
              i % 2 == 0 ? cmpAscOrder : cmpDescOrder);

        for (int j = 0; j < sizes[i]; ++j) {
            printf("\t%i", arrays[i][j]);
        }
        printf("\n");
    }

    return arrays;
}

int main() {
    solution(5);
}
