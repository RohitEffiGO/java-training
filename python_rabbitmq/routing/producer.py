import pika
from pika.exchange_type import ExchangeType

connection_param = pika.ConnectionParameters(host='localhost',port=5672)
connection = pika.BlockingConnection(connection_param)

channel = connection.channel()
channel.exchange_declare(exchange='mytopic', exchange_type=ExchangeType.topic)

message = "This is a message broadcasted from producer."
channel.basic_publish(exchange= 'mytopic', routing_key= 'place.c2', body= message)

print(f'Message sent {message}')

connection.close()