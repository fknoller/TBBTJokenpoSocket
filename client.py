import socket as s
import random
import jpysocket as jpy

def roundWinner(clientChoice, serverChoice):
  if(clientChoice == serverChoice):
    return "Tie"
  elif((clientChoice == "rock" and (serverChoice == "scissors" or serverChoice == "lizard"))
  or (clientChoice == "paper" and (serverChoice == "rock" or serverChoice == "spock"))
  or (clientChoice == "scissors" and (serverChoice == "paper" or serverChoice == "lizard"))
  or (clientChoice == "lizard" and (serverChoice == "spock" or serverChoice == "paper"))
  or (clientChoice == "spock" and (serverChoice == "scissors" or serverChoice == "rock"))):
    return "Client"
  else:
    return "Server"

HOST, PORT = s.gethostname(), 1234

sock = s.socket(s.AF_INET, s.SOCK_STREAM)
sock.connect((HOST, PORT))

scoreClient, scoreServer = 0, 0

options = ["rock", "paper", "scissors", "lizard", "Spock"]

for round in range (1, 16):
  if round == 1:
    clientChoice = random.choice(options)
  elif(result == "Server"):
    clientChoice = serverChoice
  
  msgToSend = jpy.jpyencode(clientChoice)
  sock.sendall(msgToSend)
  msgToReceive = sock.recv(1024)
  serverChoice = jpy.jpydecode(msgToReceive)
  result = roundWinner(clientChoice, serverChoice)

  if(result == "Client"):
    scoreClient += 1
  elif(result == "Server"):
    scoreServer += 1

  print("Round " + str(round) + ": " + "Client: " + str(clientChoice) + " x Server: " + str(serverChoice) + " -- Winner: " + str(result))

sock.close()

print("Final Score: Client: " + str(scoreClient) + " x Server: " + str(scoreServer))

if(scoreClient > scoreServer):
  print("Client wins!")

elif(scoreClient < scoreServer):
  print("Server wins!")

else:
  print("Tie!")