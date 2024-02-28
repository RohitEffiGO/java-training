import pika
import time
import random
from pika.exchange_type import ExchangeType


def on_message_recieved(ch, method, properties, body):
    processing_time = random.randint(1,5)
    print("Second consumer recieved: ",body, 'will take',processing_time,'seconds')
    time.sleep(processing_time)
    ch.basic_ack(delivery_tag = method.delivery_tag)
    print('Finished processing.')


connection_parameters = pika.ConnectionParameters('localhost',port=5672)
connection = pika.BlockingConnection(connection_parameters)

channel = connection.channel()
channel.exchange_declare(exchange='pubsub',exchange_type=ExchangeType.fanout)

queue = channel.queue_declare(queue='', exclusive=True)

channel.queue_bind(exchange='pubsub' ,queue=queue.method.queue)
channel.basic_qos(prefetch_count=1)

channel.basic_consume(queue=queue.method.queue,
                      auto_ack=False, 
                      on_message_callback= on_message_recieved
                      )

channel.start_consuming()