BatteryCalibration
==================

This is a simple open-source battery calibration app that requires superuser rights.

[<img src="https://fdroid.gitlab.io/artwork/badge/get-it-on.png"
      alt="Get it on F-Droid"
      height="80">](https://f-droid.org/packages/de.perflyst.batterycalibration/)
      

### How does it calibrate your battery?

The app resets your fuel gauge and the batterystats.bin file.


### Why is superuser required?

To reset the fuel gauge, it has to write into a `/sys/class/` file.
This is not possible without superuser permissions.


### Privacy

We don't collect any data on your device. No ads, and no tracking.


### Authors

* **Perflyst** - [Perflyst](https://github.com/Perflyst/)


## License

This project is licensed under the MIT License (see the [LICENSE](LICENSE) file for details).
