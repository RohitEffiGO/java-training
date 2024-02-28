import pika
import uuid

def on_message_send(ch, method, properties, body):
    print(f"Reply recieved: {body}")

connection_param = pika.ConnectionParameters(
    host='localhost',
    port=5672
)

connection = pika.BlockingConnection(connection_param)
channel = connection.channel()

client_queue = channel.queue_declare(
    queue = '',
    exclusive = True
)

channel.basic_consume(
    queue = client_queue.method.queue,
    auto_ack = True,
    on_message_callback=on_message_send
)

message = 'Can I request a reply?'

channel.queue_declare(queue='request-queue')
channel.basic_publish(
    exchange = '',
    routing_key = 'request-queue',
    properties = pika.BasicProperties(
        reply_to=client_queue.method.queue,
        correlation_id= str(uuid.uuid4()),),
    body = message
)

print('Starting client')
channel.start_consuming()