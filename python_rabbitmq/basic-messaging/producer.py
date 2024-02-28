import pika
import time
import random
from pika.exchange_type import ExchangeType

connection_parameters = pika.ConnectionParameters('localhost',port=5672)
connection = pika.BlockingConnection(connection_parameters)

channel = connection.channel()
channel.exchange_declare(exchange='pubsub',exchange_type=ExchangeType.fanout)

message = "Hello, I want to broadcast."

message_id = 1

while True: 
    channel.basic_publish(exchange='pubsub',routing_key='',body=message)
    print(message, f'iteration {message_id}')
    time.sleep(random.randint(1,3))
    message_id += 1