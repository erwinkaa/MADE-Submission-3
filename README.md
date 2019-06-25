# MADE-Submission-3
Written in Kotlin

Reviewer Note :
-Untuk alasan keamanan kredensial, sebaik API KEY tidak disematkan secara langsung di dalam sebuah kelas. Anda bisa memindahkannya ke dalam build.gradle.
-Penamaan sebuah variable sebaiknya menggunakan format camelCase. Contohnya seperti posterPath. (tapi ini sesuai response API, cara: pake Serialized)
-Biasakan untuk reformat code dan optimized import supaya code lebih rapi dan bersih dari unused import.
-Penggunaan ViewModel saat ini lebih cocok digunakan pada design pattern MVVM.
-Ketika menggunakan design pattern MVP, sebaiknya untuk menjaga state data bisa menggunakan saveInstanceState.
-Akan lebih baik jika sebuah kelas hanya memiliki satu tanggung jawab. Contoh, Anda bisa membuat tiap kelas Fragment untuk menampilkan Movie dan Tv Show.
