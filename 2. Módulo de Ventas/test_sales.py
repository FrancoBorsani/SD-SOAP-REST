from suds.client import Client
import json
hi_client= Client('http://localhost:8089/?wsdl')


hi_client.service.register('matias', 'matias', 'matias','matias', 1299)

hi_client.service.publish_category('mati')
hi_client.service.publish_product('Polo', 'Remera Blanca Polo Label', 'imagen', 1299, True, 50, 1,1)
hi_client.service.edit_product(1,'tito', 'Remera Blanca Polo Label', 'imagen', 1299, True, 50, 1,1)
print(json.loads((hi_client.service.get_products())))
print((hi_client.service.check_edit(1)))
print(json.loads((hi_client.service.get_products_bycat(1))))
print(json.loads((hi_client.service.get_products_byuser(1))))
print(json.loads((hi_client.service.get_products_byusercat(1,1))))

