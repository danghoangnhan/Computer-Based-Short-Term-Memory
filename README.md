# Computer-Based Short-Term Memory (CB-STM) in in Android Studio

The implementation of `Computer-Based Short-Term Memory` (or `CB-STM`) test proposed in the paper [Development of a Rapid Screening Tool for Mild Cognitive Impairment: Validation of a Computer-Based Short-Term Memory Test](https://www.semanticscholar.org/paper/Development-of-a-Rapid-Screening-Tool-for-Mild-of-a-Yip-Chen/179e50de6f610f9669064fbf57832a6f9915bc20) in Android Studio. (implemented in Jdk 11.0.1)

## Implementation points
* Exactly implement the models ('2NN' and 'CNN' mentioned in the paper) to have the same number of parameters written in the paper.
  * 2NN: `TwoNN` class in `models.py`; 199,210 parameters
  * CNN: `CNN` class in `models.py`; 1,663,370 parameters
* Exactly implement the non-IID data split.
  * Each client has at least two digits in case of using `MNIST` dataset.
* Implement multiprocessing of _client update_ and _client evaluation_.
* Support TensorBoard for log tracking.

## Requirements
* See `requirements.txt`

## Configurations
* See `config.yaml`

## Run
* `python3 main.py`

## Results
