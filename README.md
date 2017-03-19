# RsaUsingRandomNumbers

This program uses the Random.org website for retrieving a list of random numbers, which is then used for generating RSA key pairs.

Steps:
- Retrieve list of random numbers by making a call to the Random.org website.
- Check the list of random numbers for prime numbers, which are to be used as p and q.
- Calculate the value of n by multiplying p and q.
- Retrieve the value of Lambda, which is the LCM of p-1 and q-1.
- Retrieve list of random numbers from Random.org for calculating e.
- Compute e such that e is between 1 and (Lambda-1) which is coprime to Lambda.
- Compute the value of d such that ((d * e)% n) == 1.
- Public Key:  (n,e)
- Private Key: (n,d)


Sample Output:

Retrieving a list of random numbers from Random.org for calculating p and q
p: 29
q: 11
n: 319
Retrieving a list of random numbers from Random.org for calculating e
e: 129
d: 183
Public Key:  (319, 129)
Private Key: (319, 183)
