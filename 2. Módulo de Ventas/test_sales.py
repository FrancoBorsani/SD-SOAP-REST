from suds.client import Client
import json
hi_client= Client('http://localhost:8089/?wsdl')


hi_client.service.register('matias', 'matias', 'matias','matias', 1299, "matias@", "113014")
print("Metodo 1 funcionando")
hi_client.service.publish_category('mati')
print("Metodo 2 funcionando")
hi_client.service.publish_product('Polo', 'Remera Blanca Polo Label', 'imagen', 1299, 50,  1, 1, 1, 1)
print("Metodo 3 funcionando")

hi_client.service.edit_product(1,'tito', 'Remera Blanca Polo Label', 'imagen', 1299,  50, 1,1)

print("Metodo 4 funcionando")
print(json.loads((hi_client.service.get_products())))
print("Metodo 5 funcionando")
print((hi_client.service.check_edit(1)))
print("Metodo 6 funcionando")
print(json.loads((hi_client.service.get_products_bycat(1))))
print("Metodo 7 funcionando")
print(json.loads((hi_client.service.get_products_byuser(1))))
print("Metodo 8 funcionando")
print(json.loads((hi_client.service.get_products_byusercat(1,1))))
print("Metodo 9 funcionando")

