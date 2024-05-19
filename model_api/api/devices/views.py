from rest_framework.viewsets import ModelViewSet
from .models import DeviceModel
from .serializer import DeviceSpecificationSerializer

class DeviceViewSet(ModelViewSet):
    queryset = DeviceModel.objects.all()
    lookup_field = "id"
    serializer_class = DeviceSpecificationSerializer
