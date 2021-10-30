FROM python:3.8-buster
WORKDIR c:\\modulo_banca

COPY . c:\\modulo_banca

RUN pip install --upgrade pip
RUN pip install -r requirements.txt

EXPOSE 8082
CMD ["python", "api_modulo_banca.py.py"]