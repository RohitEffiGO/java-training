import pika
from pika.exchange_type import ExchangeType

def on_message_recieve(ch, method, properties, body):
    print(f"Consumer 1 service - Recieved new message {body}")

connection_params = pika.ConnectionParameters(host="localhost", port=5672)
connection = pika.BlockingConnection(connection_params)

channel = connection.channel()
channel.exchange_declare(exchange='mytopic', exchange_type=ExchangeType.topic)

queue = channel.queue_declare(queue='',exclusive=True)
channel.queue_bind(exchange='mytopic', queue=queue.method.queue, routing_key='#.c1')

channel.basic_consume(queue = queue.method.queue,
                      auto_ack = True,
                      on_message_callback = on_message_recieve)
print("Start Consuming")
channel.start_consuming()