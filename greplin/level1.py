from urllib2 import urlopen

def main():
  
  longest = ''

  text = urlopen('http://challenge.cueup.com/static/gettysburg.txt').read()

  for location, letter in enumerate(text):
    
    back = location
    while back != 0:
      substring = text[back:location + 1]
      if substring == substring[::-1] and len(longest) < len(substring):
        longest = substring

      back -= 1

  print 'Level 2 password: "%s"' % longest

if __name__ == '__main__':
  main()
