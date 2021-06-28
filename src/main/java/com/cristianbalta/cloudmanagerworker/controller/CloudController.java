package com.cristianbalta.cloudmanagerworker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/kube")
public class CloudController {

//    private V1PodList list;
//
//    KubeController() {
//        ApiClient client = null;
//        try {
//            client = Config.defaultClient();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Configuration.setDefaultApiClient(client);
//
//        CoreV1Api api = new CoreV1Api();
//        list = null;
//        try {
//            list = api.listNamespacedPod("default", null, null, null, null, null, null, null, null, null);
//        } catch (ApiException e) {
//            e.printStackTrace();
//        }
//        for (V1Pod item : list.getItems()) {
//            System.out.println(Objects.requireNonNull(item.getMetadata()).getName());
//        }
//
//        V1Pod pod = new V1PodBuilder()
//                .withNewMetadata()
//                .withName("apod")
//                .endMetadata()
//                .withNewSpec()
//                .addNewContainer()
//                .withName("www")
//                .withImage("nginx")
//                .endContainer()
//                .endSpec()
//                .build();
//
////        api.createNamespacedPod("default", pod, null, null, null);
//
//        try {
//            list = api.listNamespacedPod("default", null, null, null, null, null, null, null, null, null);
//        } catch (ApiException e) {
//            e.printStackTrace();
//        }
//        for (V1Pod item : list.getItems()) {
//            System.out.println(Objects.requireNonNull(item.getMetadata()).getSelfLink());
//        }
//    }
//
//    @GetMapping
//    public List<String> getPods() {
//        return list.getItems().stream().map(V1Pod::getMetadata).filter(Objects::nonNull).map(V1ObjectMeta::getName).collect(Collectors.toList());
//    }

    @GetMapping
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("DADA", HttpStatus.OK);
    }

}
