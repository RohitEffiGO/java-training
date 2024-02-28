import pika
from pika.exchange_type import ExchangeType

connection_param = pika.ConnectionParameters(host='localhost',port=5672)
connection = pika.BlockingConnection(connection_param)
channel = connection.channel()

channel.exchange_declare(exchange='headerex',exchange_type=ExchangeType.headers)

msg = 'This message will be send with headers'

channel.basic_publish(
    exchange='headerex',
    routing_key='',
    body=msg,
    properties=pika.BasicProperties(
        headers={'age':'53'}
    )
)

print('Message send from producer.')

connection.close()