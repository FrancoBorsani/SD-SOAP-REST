from ma import ma
from marshmallow import fields

class CategoriaSchema(ma.Schema):
    id = fields.Int(dump_only=True)
    name = fields.Str()
    
