import pika
from pika.exchange_type import ExchangeType

def on_message_recieve(ch,method,properties,body):
    print(f'Message recieved {body}')

connection_param = pika.ConnectionParameters(host='localhost',port=5672)
connection = pika.BlockingConnection(connection_param)
channel = connection.channel()

channel.exchange_declare(exchange='headerex',exchange_type=ExchangeType.headers)
channel.queue_declare(queue='queue',exclusive=True)

bind_args = {
    'x-match':'any',
    'name':'brian',
    'age':'53'
}

channel.queue_bind(queue='queue',exchange='headerex',routing_key='',arguments=bind_args)

channel.basic_consume(
    queue='queue',
    auto_ack=True,
    on_message_callback=on_message_recieve
)

print('Starting consuming')
channel.start_consuming()