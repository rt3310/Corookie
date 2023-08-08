FROM nginx

RUN mkdir /app
WORKDIR /app

RUN mkdir ./dist

ADD ./dist ./dist

# RUN rm /etc/nginx/conf.d/default.conf

# COPY ./nginx.conf /etc/nginx/conf.d

ENTRYPOINT [ "yarn", "serve", "-s", "dist" ]