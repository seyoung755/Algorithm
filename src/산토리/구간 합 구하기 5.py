import sys

input = sys.stdin.readline

N, M = list(map(int, input().split()))

board = []

for _ in range(N):
    board.append(list(map(int, input().split())))

# 누적 합 배열 s 구하기
# (r1, c1)을 구할 때 -> s[r1][c1] = s[r1][c1-1] + s[r1-1][c1] - s[r1-1][c1-1] + board[r1][c1]
# 역시, c1-1이나 r1-1이 0보다 작으면 중복해서 더하는 부분이 없기 때문에 s[r1-1][c1-1]을 빼지 않아도 됨.

s = [[0 for _ in range(N)] for _ in range(N)]

for r in range(N):
    for c in range(N):
        bound_r, bound_c = r-1, c-1
        if bound_r >= 0 and bound_c >= 0:
            s[r][c] = s[r][bound_c] + s[bound_r][c] + board[r][c] - s[bound_r][bound_c]
        elif bound_r >= 0:
            s[r][c] = s[bound_r][c] + board[r][c]
        elif bound_c >= 0:
            s[r][c] = s[r][bound_c] + board[r][c]
        else:
            s[r][c] = board[r][c]

# 합을 구할 때마다 NxN 배열을 모두 순회하면 시간초과가 발생할 것이다.
# 입력받고 임의의 r,c에 대해 (0,0) ~ (r,c)의 합을 구한 별도의 NxN 배열을 만들자.
# (r1, c1) ~ (r2, c2)의 합을 구하려면, 
# 누적합 배열에서 (r2, c2) - (r2, c1-1) - (r1-1, c2) + (r1-1, c1-1)을 구하면 된다.
# c1-1 이나 r1-1 이 0보다 작다면?
# 중복해서 빠지는 부분이 없기 때문에 (r1-1, c1-1)을 더하지 않아도 된다.

for _ in range(M):
    answer = 0
    r1, c1, r2, c2 = list(map(int, input().split()))
    r1 -= 1
    c1 -= 1
    r2 -= 1
    c2 -= 1

    bound_r, bound_c = r1-1, c1-1
    if bound_r >= 0 and bound_c >= 0:
        answer = s[r2][c2] - s[r2][bound_c] - s[bound_r][c2] + s[bound_r][bound_c]
    elif bound_r >= 0:
        answer = s[r2][c2] - s[bound_r][c2]
    elif bound_c >= 0:
        answer = s[r2][c2] - s[r2][bound_c]
    else:
        answer = s[r2][c2]
    print(answer)