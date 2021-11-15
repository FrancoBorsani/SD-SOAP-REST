from ma import ma
from marshmallow import fields

class UserSchema(ma.Schema):
    id = fields.Int(dump_only=True)
    name = fields.Str()
    lastname= fields.Str()
    username = fields.Str()
    dni = fields.Int()

