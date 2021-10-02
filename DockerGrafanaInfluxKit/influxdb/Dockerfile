FROM influxdb:1.3.1-alpine

WORKDIR /app
COPY influxdb.conf /etc/influxdb/influxdb.conf
COPY entrypoint.sh ./
RUN chmod u+x entrypoint.sh

ENTRYPOINT ["/app/entrypoint.sh"]
