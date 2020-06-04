FROM node:alpine3.11
LABEL maintainer="goran.kuljanin@uns.ac.rs"
WORKDIR /app
COPY package.json ./
RUN ["npm", "install"]
COPY . .
EXPOSE 4200
CMD [ "npm", "run", "start"]
