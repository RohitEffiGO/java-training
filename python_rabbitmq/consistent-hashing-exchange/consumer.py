import pika
from pika.exchange_type import ExchangeType

def on_message1_recieve(ch,method,properties,body):
    print(f'Message recieved {body}, this is for queue 1.')

def on_message2_recieve(ch,method,properties,body):
    print(f'Message recieved {body} this is for queue 2.')

connection_param = pika.ConnectionParameters(host='localhost',port=5672)
connection = pika.BlockingConnection(connection_param)
channel = connection.channel()

channel.exchange_declare('simplehashing','x-consistent-hash')
channel.queue_declare(queue='queue1',exclusive=True)
channel.queue_declare(queue='queue2',exclusive=True)

channel.queue_bind(queue='queue1',exchange='simplehashing',routing_key='1')
channel.queue_bind(queue='queue2',exchange='simplehashing',routing_key='1')

channel.basic_consume(
    queue='queue1',
    auto_ack=True,
    on_message_callback=on_message1_recieve
)

channel.basic_consume(
    queue='queue2',
    auto_ack=True,
    on_message_callback=on_message2_recieve
)

print('Starting consuming')
channel.start_consuming()