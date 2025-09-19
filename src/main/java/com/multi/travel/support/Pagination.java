package com.multi.travel.support;

public class Pagination {

    private final int page;        // 현재 페이지 번호 (1부터 시작)
    private final int size;        // 한 페이지에 보여줄 글 개수
    private final int total;       // 전체 글 개수
    private final int totalPages;  // 전체 페이지 수
    private final int block;       // 페이지 블록 크기 (예: 5 → [1][2][3][4][5])
    private final int startPage;   // 현재 블록의 시작 페이지 번호
    private final int endPage;     // 현재 블록의 끝 페이지 번호

    // 생성자: page(현재 페이지), size(페이지 크기), total(전체 건수), block(블록 크기)로 계산
    public Pagination(int page, int size, int total, int block) {
        this.page = Math.max(1, page);   // 현재 페이지는 최소 1 이상
        this.size = Math.max(1, size);   // 페이지 크기는 최소 1 이상
        this.total = Math.max(0, total); // 전체 건수는 최소 0 이상
        this.totalPages = (int) Math.ceil((double) this.total / this.size);
        // 총 페이지 수 = 전체 글 수 / 페이지 크기 (올림)

        this.block = Math.max(1, block); // 블록 크기는 최소 1 이상

        int currentBlock = (int) Math.ceil((double) this.page / this.block);
        // 현재 블록 번호 = 현재 페이지 / 블록 크기 (올림)

        this.startPage = (currentBlock - 1) * this.block + 1;
        // 블록 시작 페이지 = (현재 블록-1) * 블록 크기 + 1

        int ep = this.startPage + this.block - 1;
        // 블록 끝 페이지 후보 = 시작 페이지 + 블록 크기 - 1

        this.endPage = Math.min(ep, Math.max(1, this.totalPages));
        // 실제 끝 페이지 = 총 페이지 수와 비교하여 작은 값 선택
    }

    public int getPage() { return page; }              // 현재 페이지 번호 반환
    public int getSize() { return size; }              // 페이지 크기 반환
    public int getTotal() { return total; }            // 전체 글 개수 반환
    public int getTotalPages() { return totalPages; }  // 전체 페이지 수 반환
    public int getBlock() { return block; }            // 블록 크기 반환
    public int getStartPage() { return startPage; }    // 블록 시작 페이지 반환
    public int getEndPage() { return endPage; }        // 블록 끝 페이지 반환

    public boolean isHasPrev() { return startPage > 1; }
    // 이전 블록이 존재하는지 확인 (시작 페이지가 1보다 크면 true)

    public boolean isHasNext() { return endPage < totalPages; }
    // 다음 블록이 존재하는지 확인 (끝 페이지 < 전체 페이지면 true)

    public boolean isHasNextPage() { return page < totalPages; }

    public boolean isHasPrevPage() { return page > 1; }

    public int getPrevPage() { return Math.max(1, startPage - 1); }
    // 이전 블록으로 이동할 때의 페이지 번호 (최소 1)

    public int getNextPage() { return Math.max(1, Math.min(totalPages, endPage + 1)); }
    // 다음 블록으로 이동할 때의 페이지 번호 (총 페이지 수 넘지 않도록 제한)

    public int getOffset() { return (page - 1) * size; }
    // SQL LIMIT 쿼리에서 사용할 offset 값 계산 (시작 위치)
    public int getNext(){   return Math.min(totalPages, page + 1); }

    public int getPrev(){   return Math.max(1, page - 1); }
}
