#! /usr/bin/python
def main():
  numbers = [3, 4, 9, 14, 15, 19, 28, 37, 47, 50, 54, 56, 59, 61, 70, 73, 78, 81, 92, 95, 97, 99]

  max_number = max(numbers)
  counts = {0: 1}
  answer = 0
  for number in numbers:
    if number in counts:
      answer += counts[number]
    prev = [(s,c) for (s, c) in counts.iteritems()]
    for (s, c) in prev:
      val = s+number;
      if max_number < val:
        continue
      if val not in counts:
        counts[val] = c
      else:
        counts[val] += c
  print answer

if __name__ == '__main__':
  main()
