import pika
from pika.exchange_type import ExchangeType

connection_param = pika.ConnectionParameters(host='localhost',port=5672)
connection = pika.BlockingConnection(connection_param)

channel = connection.channel()
channel.exchange_declare(exchange='firstexchange',exchange_type=ExchangeType.direct)
channel.exchange_declare(exchange='secondexchange',exchange_type=ExchangeType.fanout)
channel.exchange_bind('secondexchange','firstexchange')

msg = 'This has come from first exchange.'
channel.basic_publish(exchange='firstexchange',
                      routing_key='',
                      body=msg)
print(f'Message sent {msg}')
connection.close()