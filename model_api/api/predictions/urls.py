
from django.urls import path
from .views import DevicePriceClassifyView, DeviceSpecificationsPriceClassifyView

urlpatterns = [
    path("device-spec/", DeviceSpecificationsPriceClassifyView.as_view(), name="devices_specification_predict_view"),
    path("<uuid:id>/", DevicePriceClassifyView.as_view(), name="devices_predict_view"),
]
