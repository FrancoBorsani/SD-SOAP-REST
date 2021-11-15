from ma import ma
from marshmallow import fields

class BankSchema(ma.Schema):
    id = fields.Int(dump_only=True)
    banco = fields.Str()
    cbu = fields.Int()
    user_id = fields.Int()
