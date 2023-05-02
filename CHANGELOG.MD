# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.2.0] - 2023-04-28
### Added
- Support for custom normalization of the user agent string using new `Normalizer` API. There is no behaviour change, but a factory method to get a singleton `UserAgent` instance is deprecated and you should either use a constructor without `Normalizer` argument that preserves the current behaviour or use a version with `Normalizer`. More info in README or documentation of the API.

### Changed
- Whole library rewritten to Kotlin. Should be 100% compatible with Java clients.

## [1.1.0] - 2021-09-13
### Changed
- Migration from jCenter to Maven Central 🎉
- ‼️ Important ‼️ groupId has changed. New groupId is `io.github.ackeecz`.
