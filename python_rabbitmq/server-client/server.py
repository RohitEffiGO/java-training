import pika

def on_request_message_recieved(ch,method,properties,body):
    print(f'Request recieved: {properties.correlation_id}')
    ch.basic_publish(
        '',
        routing_key=properties.reply_to,
        body=f'hey its your reply to {properties.correlation_id}'
        )

connection_param = pika.ConnectionParameters(host="localhost", port=5672)
connection = pika.BlockingConnection(connection_param)

channel = connection.channel()

channel.queue_declare(queue='request-queue')

channel.basic_consume(
    queue='request-queue',
    auto_ack=True,
    on_message_callback=on_request_message_recieved
    )

print("Starting Server")
channel.start_consuming()