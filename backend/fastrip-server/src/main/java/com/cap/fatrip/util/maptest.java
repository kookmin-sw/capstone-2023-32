package com.cap.fatrip.util;
import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class maptest {
    public static void main(String[] args) {
        // Google Maps API 클라이언트 초기화
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("YOUR-KEY")
                .build();

        // 검색할 위치
        String location = "제주도";

        try {
            // 장소 검색 요청
            PlacesSearchResponse response = PlacesApi.textSearchQuery(context, location).await();

            // 결과에서 평점이 높은 상위 3곳 추출
            PlacesSearchResult[] results = response.results;
            if (results.length > 0) {
                // 평점을 기준으로 정렬
                Arrays.sort(results, Comparator.comparingDouble(place -> -place.rating));

                // 상위 3곳을 출력
                int limit = Math.min(3, results.length);
                for (int i = 0; i < limit; i++) {
                    PlacesSearchResult result = results[i];
                    System.out.println("장소 이름: " + result.name);
                    System.out.println("평점: " + result.rating);
                    System.out.println("위치: " + result.geometry.location);
                    System.out.println("------------------------");
                }
            } else {
                System.out.println("검색 결과가 없습니다.");
            }
        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
