from ma import ma
from marshmallow import fields

class ProductSchema(ma.Schema):
    id = fields.Int(dump_only=True)
    descripcioncorta = fields.Str()
    descripcionlarga = fields.Str()
    imagen = fields.Str()
    stock = fields.Int()
    visible = fields.Boolean()
    categoria_id_categoria = fields.Int()
    precio = fields.Float()
