import pika
from pika.exchange_type import ExchangeType

def on_message_recive(ch,method,properties,body):
    print(f'Message recieved: {body}')

connection_param = pika.ConnectionParameters(host='localhost',port=5672)
connection = pika.BlockingConnection(connection_param)
channel = connection.channel()

channel.exchange_declare(exchange='secondexchange',exchange_type=ExchangeType.fanout)
channel.queue_declare(queue='queue',exclusive=True)
channel.queue_bind(queue='queue',exchange='secondexchange')
channel.basic_consume(
    queue='queue',
    auto_ack=True,
    on_message_callback=on_message_recive
)
print('Start consuming')
channel.start_consuming()