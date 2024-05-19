from django.db import models
from uuid import uuid4

class DeviceModel(models.Model):
    class Meta:
        db_table = "Device"
        
    id = models.UUIDField("ID", default=uuid4, primary_key=True, db_index=True, editable=False)
    battery_power = models.IntegerField()
    blue = models.IntegerField()
    clock_speed = models.FloatField()
    dual_sim = models.IntegerField()
    fc = models.FloatField()
    four_g = models.FloatField()
    int_memory = models.FloatField()
    m_dep = models.FloatField()
    mobile_wt = models.FloatField()
    n_cores = models.FloatField()
    pc = models.FloatField()
    px_height = models.FloatField()
    px_width = models.FloatField()
    ram = models.FloatField()
    sc_h = models.FloatField()
    sc_w = models.FloatField()
    talk_time = models.IntegerField()
    three_g = models.IntegerField()
    touch_screen = models.IntegerField()
    wifi = models.IntegerField()
