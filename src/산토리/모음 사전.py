from itertools import product

def solution(word):
    # 모든 경우의 수를 정렬해둔 다음 (5^5) 해당 단어 찾기
    paths = ['A', 'E', 'I', 'O', 'U']
    cnt = 0
    cases = []
    for i in range(1, 6):
        for case in list(product(paths, repeat=i)):
            cases.append("".join(case))

    cases = sorted(cases)

    return cases.index(word) + 1
