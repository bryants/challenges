def fizz0():
  global fizz
  fizz = fizz1
  return "Fizz"

def fizz1():
  global fizz
  fizz = fizz2
  return ""

def fizz2():
  global fizz
  fizz = fizz0
  return ""

def buzz0():
  global buzz
  buzz = buzz1
  return "Buzz"

def buzz1():
  global buzz
  buzz = buzz2
  return ""

def buzz2():
  global buzz
  buzz = buzz3
  return ""

def buzz3():
  global buzz
  buzz = buzz4
  return ""

def buzz4():
  global buzz
  buzz = buzz0
  return ""

fizz = fizz1
buzz = buzz1

for n in range(1,101):
  print fizz() + buzz() or n
