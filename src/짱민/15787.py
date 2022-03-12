import sys;input=sys.stdin.readline

N,M=map(int,input().split())

train = list( 0 for _ in range(N) )

for _ in range(M):
    command=list(map(int,input().split()))
    
    if command[0] == 1:
        i=command[1]-1
        x=command[2]-1
        train[i]=train[i] | 1 << x

    elif command[0] == 2:
        i=command[1]-1
        x=command[2]-1
        train[i] = train[i] & ~(1<<x)

    elif command[0] == 3:
        i=command[1]-1
        train[i] = train[i] << 1
        train[i] = train[i] & ~(1 << 20)

    else:
        i=command[1]-1
        train[i] = train[i]>>1

print(len(set(train)))
ans=0
for i in range(N):
    flag=True
    for j in range(0,i):
        if train[i]==train[j]:
            flag=False
            break
    if flag:
        ans+=1

print(ans)
