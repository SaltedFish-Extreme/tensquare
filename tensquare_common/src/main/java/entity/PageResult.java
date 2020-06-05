package entity;

import lombok.Data;
import lombok.ToString;
import lombok.Value;

import java.util.List;

@ToString
@Data
@Value
public class PageResult<T> {
    private long total;
    private List<T> rows;
}
