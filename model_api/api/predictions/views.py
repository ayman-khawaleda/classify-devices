from rest_framework.generics import RetrieveAPIView, CreateAPIView
from rest_framework.request import Request
from rest_framework.response import Response
from rest_framework import status
from predictions.utils.model import DevicePriceClassifier
from devices import serializer
from drf_spectacular.utils import extend_schema

class DevicePriceClassifyView(RetrieveAPIView):
    serializer_class  = serializer.DeviceIDSerializer
    
    def retrieve(self, request: Request, *args, **kwargs):
        
        serializer = self.serializer_class(data=kwargs)
        serializer.is_valid(raise_exception=True)
        prediction = DevicePriceClassifier.predict(serializer.get_device_data())
        return Response(data={"prediction": prediction}, status=status.HTTP_200_OK)

class DeviceSpecificationsPriceClassifyView(CreateAPIView):
    serializer_class  = serializer.DeviceSpecificationSerializer
    
    @extend_schema(responses=[serializer.DeviceSpecificationSerializerResponse])
    def create(self, request: Request, *args, **kwargs):
        serializer = self.serializer_class(data=request.data,context = self.get_serializer_context())
        serializer.is_valid(raise_exception=True)
        prediction = DevicePriceClassifier.predict(serializer.data)
        return Response(data={"prediction": prediction}, status=status.HTTP_200_OK)
