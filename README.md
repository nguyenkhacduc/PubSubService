# Publisher-Subscriber Service

This is an example of Publisher-Subscriber Service using a queue to deploy formulas.

Publishers put two numbers and an operator(+, -, * or /) to formulaQueue.

Subscribers get information from formulaQueue and transform to operation.

# Note

This service does not run in real-time. Both Publisher and Subscriber is handled by Driver and PubSubService class.

# Idea

The Formula class contains information and calculates to get an operation.

The Publisher class manipulates data to formulaQueue. 

The Subscriber class manages topics for a subscriber and get information from queue whenever it has.

The PubSubService class implements methods of Publisher and Subscriber class.

The Driver class handles input from users and controls the service.

Whenever a publisher has a request to make a calculation, the datas will be sent to formulaQueue. The formulaQueue keeps information until there is a request from a subscriber that wants to get datas with a specified topic. The datas now is calculated and stored in that subscriber. This service also allows to print all formulas in a subscriber.

# Instruction
Input command:
- plus, minus, multiply, divide: a request to make a calculation.
- get: a request to get datas from a subscriber ("all" for broadcast).
- print: print all formulas of subscriber.
- exit

# References
http://www.code2succeed.com/pub-sub/

