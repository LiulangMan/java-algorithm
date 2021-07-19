# writer by zjw
class A:
    p = 0
    # 算子
    s = [[0 for i in range(2)] for j in range(2)]
    s[0][0] = 1
    s[0][1] = 1
    s[1][0] = 1

    # 单位矩阵
    e = [[0 for l in range(2)] for k in range(2)]
    e[0][0] = 1
    e[1][1] = 1

    def __init__(self, p):
        self.p = p
        pass

    def mut(self, s0, s1):

        a1 = (s0[0][0] * s1[0][0] % self.p + s0[0][1] * s1[1][0] % self.p) % self.p
        a2 = (s0[0][0] * s1[0][1] % self.p + s0[0][1] * s1[1][1] % self.p) % self.p
        a3 = (s0[1][0] * s1[0][0] % self.p + s0[1][1] * s1[1][0] % self.p) % self.p
        a4 = (s0[1][0] * s1[0][1] % self.p + s0[1][1] * s1[1][1] % self.p) % self.p

        return [[a1, a2], [a3, a4]]

    def pow(self, num):
        k = self.s  # 算子
        e1 = self.e  # 单位矩阵
        while num != 0:
            if num & 1 == 1:
                e1 = self.mut(e1, k)  # e1 = e1 * k   O(logn)

            k = self.mut(k, k)  # k倍增
            num >>= 1  # 右移一位 1101   -->  0110

        return e1

    def mut1(self, s0, s1):

        a1 = s0[0][0] * s1[0][0] + s0[0][1] * s1[1][0]
        a2 = s0[0][0] * s1[0][1] + s0[0][1] * s1[1][1]
        a3 = s0[1][0] * s1[0][0] + s0[1][1] * s1[1][0]
        a4 = s0[1][0] * s1[0][1] + s0[1][1] * s1[1][1]

        return [[a1, a2], [a3, a4]]

    def pow1(self, num):
        k = self.s  # 算子
        e1 = self.e  # 单位矩阵
        while num != 0:
            if num & 1 == 1:
                e1 = self.mut1(e1, k)  # e1 = e1 * k   O(logn)

            k = self.mut1(k, k)  # k倍增
            num >>= 1  # 右移一位 1101   -->  0110
        return e1

    #
    # def swap(self, a1, a2):  # a1:1101 0111
    #     a1 = a1 ^ a2  # a1 = 1010
    #     a2 = a1 ^ a2  # a2 = 1101
    #     a1 = a1 ^ a2  # a1 = 0111


def df(n):
    global cout
    cout += 1
    print(cout)
    if n == 0:
        return 0
    if n == 1:
        return 1
    return df(n - 1) + df(n - 2)


def solve2(n, m, p):
    global ans
    a = A(p)
    if n == 1:
        return 1
    if n == 2:
        return 2

    if m >= n + 2:
        s_n = a.pow(n)
        ans = s_n[0][0] + s_n[1][0] - 1  # s(n) = f(n+2) - 1
        ans = ans % p
        return
    else:
        # s_n = a.pow1(n)
        # ans = s_n[0][0] + s_n[1][0] - 1  # s(n) = f(n+2) - 1

        s_m = a.pow1(m - 2)
        f_m = s_m[0][0] + s_m[1][0]  # f(m)

        b = A(f_m)
        s_n = b.pow1(n)
        ans = s_n[0][0] + s_n[1][0] - 1  # s(n) = f(n+2) - 1

        ans = ans % f_m % p
    return


if __name__ == "__main__":
    cout = 0
    ans = 0
    s0 = input().split()
    n = int(s0[0])
    m = int(s0[1])
    p = int(s0[2])  # 10^18

    solve2(n, m, p)
    print(ans)

    # m1 = df(m)
    # ans = df(n + 2) - 1
    # ans = ans % m1 % p
    # print(ans)
