#!/usr/bin/env python

import math

def isPrime(num):
  for i in range(2, num):
    if num % i == 0:
      return False
  return True

def fibonacci():
  x, y = 0, 1
  while True:
    yield x
    x, y = y, x + y

def nextFibonacci(num):
  fibo = fibonacci()
  next = fibo.next()
  while next <= num:
    next = fibo.next
  return next

def primeFibonacci(num):
  next = nextFibonacci(num)
  while True:
    if isPrime(next):
      return next
    next = nextFibonacci(next)

def getPrime():
  n = 2
  while True:
    if isPrime(n):
      yield n
    n += 1

def primeDivisor(num):
  prime = getPrime()
  divisor = prime.next()
  while divisor <= math.ceil(math.sqrt(num)):
    if num % divisor == 0:
      return divisor
    divisor = prime.next()

def divisors(num):
  divisors = []
  while True:
    divisor = primeDivisor(num)
    divisors.append(divisor)
    if isPrime(num):
      break
    num /= divisor
  return divisors

def main():
  num = primeFibonacci(227000)
  num += 1
  print 'start with: "%d"' % num
  divisors = divisors(num)
  print 'result: "%d"' % reduce(lambda x, y: x+ y, divisors)

if __name__ == '__main__':
  main()

