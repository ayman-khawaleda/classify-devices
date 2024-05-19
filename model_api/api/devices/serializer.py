from rest_framework import serializers
from rest_framework.exceptions import NotFound
from .models import DeviceModel


class DeviceIDSerializer(serializers.Serializer):
    id = serializers.UUIDField(required=True)
    
    def get_device_data(self):
        try:
            ins = DeviceModel.objects.get(id = self.data["id"])
            return DeviceSpecificationSerializer(instance=ins).data
        except DeviceModel.DoesNotExist:
            raise NotFound

class DeviceSpecificationSerializer(serializers.ModelSerializer):
    class Meta:
        model = DeviceModel
        fields = "__all__"
        
    def to_representation(self, instance):
        data = super().to_representation(instance)
        data["d_volume"] = data["sc_h"] * data["sc_w"] * data["m_dep"]
        return data


class DeviceSpecificationSerializerResponse(serializers.Serializer):
    prediction = serializers.FloatField()
