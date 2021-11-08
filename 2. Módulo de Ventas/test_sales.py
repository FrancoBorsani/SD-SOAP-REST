from suds.client import Client

hi_client= Client('http://localhost:8089/?wsdl')


#hi_client.service.register('matias', 'matias', 'matias','matias', 1299)

hi_client.service.publish_category('Polo')
hi_client.service.publish_product('Polo', 'Remera Blanca Polo Label', 'imagen', 1299, True, 50, 2)