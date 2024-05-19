from django.urls import path, include
from rest_framework.routers import SimpleRouter
from . import views
router = SimpleRouter()
router.register(
    "", viewset=views.DeviceViewSet, basename="devices_view_set"
)

urlpatterns = [
    path("", include(router.urls), name="devices_view_set")
]
