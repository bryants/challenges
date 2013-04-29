def digiRoot(n):
  if n < 10:
    return n

  return digiRoot((n % 10) + digiRoot((int)(n / 10)))
