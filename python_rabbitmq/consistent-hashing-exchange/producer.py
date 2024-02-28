import pika
from pika.exchange_type import ExchangeType

connection_param = pika.ConnectionParameters(host='localhost',port=5672)
connection = pika.BlockingConnection(connection_param)
channel = connection.channel()

channel.exchange_declare('simplehashing','x-consistent-hash')

routing_key = 'hash mesdfasdf'
# msg = 'This message will be send using consistent-hashing-exchange'
msg = '#'
channel.basic_publish(
    exchange='simplehashing',
    routing_key=routing_key,
    body=msg
)

print('Message sent from producer.')

connection.close()