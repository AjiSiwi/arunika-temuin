<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/AjiSiwi/arunika-temuin/images">
    <img src="logo.png" alt="Logo" width="240" height="240">
  </a>

  <h3 align="center">TEMU.IN DOCUMENTATION</h3>

</p>



<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#Background">About The Project</a>
      <ul>
        <li><a href="#Project Innovation">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## BACKGROUND

The development of human resources quality, especially the younger generation, is an important part of Indonesia's National Development. However, many teenagers are confused about how to match their interests and personalities with a suitable self-development community and cause them to spend their time on not useful things. So we need to help Indonesian youth to find interests that align with their personalities represented by a community to help them improve their skills in a positive way. What we have done:
1.  Machine Learning: 
- Preprocessing RIASEC Dataset
- Do feature selection
- Create classification model with Tensorflow for community recommendation system. 
- Preprocessing stackoverflow threads dataset
- Create classification model with Tensorflow to detect which threads are useful in our platform. 
2.  Android:
- Create an android application which has a Sign in/Login feature
- personality test
- discussion forum
- search features.
3. Cloud: 
- Design system architecture to connect android apps with our database. We use 2 system architecture services: GCP for data storage and API generation and Firebase for authentication listener and liaison with Firestore and storage


## PROJECT INNOVATION

1. uses AI to predict the appropriate community for a person based on some psychological questionnaire
2. Provide Community Forum and filter the threads inside based on content usefulness

## APPLICATION PREVIEW

## Cloud Computing

### Deployment
  1. Storing Model to GCP cloud storage
    1. Open gcp console
    2. Make a cloud storage bucket
    3. Store .h5 and .pkl model to cloud storage.

  2. Setting Up Virtual Environtment
    1. Open gcp console
    2. Open cloud shell terminal
    3. Setting up pyhton 3 venv:
      cd your-project
      python3 -m venv env

		source env/bin/activate

  3. Make Cloud Funtions
    a. Prediction API
      1. Go to Cloud Funtions Console
      2. Create Functions
        1. Function Name : Up to you, it will be an endpoint
        2. Region  : asia-southeast2
        3. HTTP
          - trigger type: HTTP
          - Authentication: Allow unauthenticated invocations
        4. Runtime service account : App Engine
        5. Memory Allocated: 512 Mib
        6. Timeout : 90
        7. Make code functions: 

    b. Get Communities API
      1. Go to Cloud Funtions Console
      2. Create Functions
        1. Function Name : Up to you, it will be an endpoint
        2. Region  : asia-southeast2
        3. HTTP
          - trigger type: HTTP
          - Authentication: Allow unauthenticated invocations
        4. Runtime service account : App Engine
        5. Memory Allocated: 1 Gib
        6. Timeout : 120
        7. Make code functions : 






