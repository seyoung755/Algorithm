from itertools import combinations
from collections import Counter


def solution(orders, course):
    answer = set()

    for n in course:
        result = []
        for order in orders:
            comb = list(combinations(order, n))
            for c in comb:
                c = "".join(sorted(c))
                result.append(c)

            counter = Counter(result)

        m = False
        for menu, cnt in counter.most_common():
            if cnt < 2:
                break
            if not m:
                m = cnt
            else:
                if m > cnt:
                    break

            answer.add(menu)
    # print(answer)
    return sorted(answer)