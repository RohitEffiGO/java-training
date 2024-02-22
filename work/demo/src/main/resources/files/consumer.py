import pika

def on_message_recieved(ch, method, properties, body):
    print("recieved",body)

connection_parameters = pika.ConnectionParameters('localhost',port=8080)
connection = pika.BlockingConnection(connection_parameters)

channel = connection.channel()
channel.queue_declare(queue='letterbox')
channel.basic_consume(queue='letterbox',auto_ack=True, on_message_callback= on_message_recieved)

channel.start_consuming()